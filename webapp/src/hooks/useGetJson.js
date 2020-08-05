import { useState, useEffect, useCallback, useRef } from 'react'
import { getJSON } from '@acto/ajax'

export default function useGetJson (url, deps = [], shouldGet = true) {
    const [res, setRes] = useState({ data: null, loading: true, error: null })

    const controllerRef = useRef(null)

    const get = useCallback(() => {
        controllerRef.current = new AbortController()
        getJSON(url, controllerRef.current)
            .notFound(() => setRes({ data: null, loading: false, error: 404 }))
            .forbidden(() => setRes({ data: null, loading: false, error: 403 }))
            .json(data => setRes({ data, loading: false, error: null }))
            .catch(err => { setRes({ data: null, loading: false, error: err }) })
    }, [url])

    const cleanup = useCallback(() =>{
        controllerRef.current.abort()
    }, [controllerRef])

    useEffect(() => {
        get()
        return cleanup
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [get, cleanup, ...deps])

    return res
}
